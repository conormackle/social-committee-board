import {Buffer} from 'buffer'

export function setAccessToken(token) {
  window.localStorage.setItem('accessToken', token)
}

export function setRefreshToken(token) {
  window.localStorage.setItem('refreshToken', token)
}

export function getAccessToken() {
  return window.localStorage.getItem('accessToken')
}

export function getRefreshToken() {
  return window.localStorage.setItem('refreshToken')
}

export function removeAccessToken() {
  window.localStorage.removeItem('accessToken')
}

export function removeRefreshToken() {
  window.localStorage.removeItem('refreshToken')
}

export function getPayload() {
  const token = getAccessToken()
  console.log('token', token)
  if (!token) return false
  const parts = token.split('.')
  console.log('parts', parts)
  if (parts.length < 3) return false
  return JSON.parse(Buffer.from(parts[1], 'base64'))
}

export function isAccessTokenValid() {
  const payload = getPayload()
  console.log('payload', payload)
  if (!payload) return false
  const now = Math.round(Date.now() / 1000)
  return now < payload.exp
}

export function isAuthenticated() {
  if (isAccessTokenValid()) return true
  const refreshToken = getRefreshToken()
  if (!refreshToken) return false

}