import { Buffer } from 'buffer'
import { post } from 'axios'

const ZOHO_CLIENT_ID = process.env.REACT_APP_ZOHO_CLIENT_ID
const ZOHO_CLIENT_SECRET = process.env.REACT_APP_ZOHO_CLIENT_SECRET
const REDIRECT_URI = process.env.REACT_APP_REDIRECT_URI

const domain = 'https://accounts.zoho.com'
const scope = 'ZOHOPEOPLE.forms.ALL,Aaaserver.profile.read'
const responseType = 'code'
const accessType = 'offline'

export function zohoRedirect() {
  return fetch(
    `${domain}/oauth/v2/auth?scope=${scope}&client_id=${ZOHO_CLIENT_ID}&response_type=${responseType}&access_type=${accessType}&redirect_uri=${REDIRECT_URI}&prompt=consent`,
    { redirect: 'manual' }
  )
}
export function getZohoToken(code) {
  return post(`${domain}/oauth/v2/token?access_type=offline&scope=${scope}&grant_type=authorization_code&client_id=${ZOHO_CLIENT_ID}&redirect_uri=${REDIRECT_URI}&client_secret=${ZOHO_CLIENT_SECRET}&code=${code}&response_type=code&prompt=consent`)
}

export async function refreshZohoToken(refreshToken) {
  const { data } = await post(`${domain}/oauth/v2/token?refresh_token=${refreshToken}&client_id=${ZOHO_CLIENT_ID}&client_secret=${ZOHO_CLIENT_SECRET}&grant_type=refresh_token`)
  if (data.access_token) {
    setAccessToken(data.access_token)
    setAccessTokenExpiry(data.expires_in)
    return true
  } return false
}

export function setAccessToken(token) {
  window.localStorage.setItem('accessToken', token)
}

export function setAccessTokenExpiry(exp) {
  window.localStorage.setItem('tokenExpiry', Date.now() + exp * 1000)
}

export function setRefreshToken(token) {
  window.localStorage.setItem('refreshToken', token)
}

export function getAccessToken() {
  return window.localStorage.getItem('accessToken')
}

function getAccessTokenExpiry() {
  return window.localStorage.getItem('tokenExpiry')
}

export function getRefreshToken() {
  return window.localStorage.getItem('refreshToken')
}

function removeAccessToken() {
  window.localStorage.removeItem('accessToken')
}

function removeAccessTokenExpiry() {
  window.localStorage.removeItem('tokenExpiry')
}

function removeRefreshToken() {
  window.localStorage.removeItem('refreshToken')
}

export function logout() {
  removeAccessToken()
  removeAccessTokenExpiry()
  removeRefreshToken()
}

export function getPayload() {
  const token = getAccessToken()
  if (!token) return false
  const parts = token.split('.')
  if (parts.length < 3) return false
  return JSON.parse(Buffer.from(parts[1], 'base64'))
}

export function isTokenValid() {
  const token = getAccessToken()
  if (!token) return false
  const expiryTime = getAccessTokenExpiry()
  if (!expiryTime) return false
  if (Date.now() < expiryTime) return true
  removeAccessTokenExpiry()
  return false
}

export function isAuthenticated() {
  if (isTokenValid()) return true
  const refreshToken = getRefreshToken()
  if (!refreshToken) return false
  refreshZohoToken()
  return true
}