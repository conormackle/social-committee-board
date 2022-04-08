import { post } from 'axios'

const domain = 'https://accounts.zoho.com'
const scope = 'ZOHOPEOPLE.forms.ALL,Aaaserver.profile.read'
const responseType = 'code'
const accessType = 'online'
const REDIRECT_URI = 'http://localhost:3000/'
const zohoClientId = process.env.REACT_APP_ZOHO_CLIENT_ID
const client_secret = process.env.REACT_APP_ZOHO_CLIENT_SECRET
export function zohoRedirect() {
  return post(
    `${domain}/oauth/v2/auth?scope=${scope}&client_id=${zohoClientId}&response_type=${responseType}&access_type=${accessType}&redirect_uri=${REDIRECT_URI}`,
    { redirect: 'manual' }
  )
}
export function getZohoToken(code) {
  // return post(`${domain}oauth/v2/token?access_type=offline&grant_type=authorization_code&client_id=${zohoclientId}&client_secret=57cdb3091dc7a8407f6f0766fa074ff9320eebafbd&redirect_uri=http://localhost/&code=1000.0a750526a89f0981c6bc6bb5843dd1ed.ef6c3c749095ccbbfa05e8f22883f6a4&scope=ZOHOPEOPLE.forms.ALL&response_type=code`)
  return post(`${domain}/oauth/v2/token?access_type=offline&scope=${scope}&grant_type=authorization_code&client_id=${zohoClientId}&redirect_uri=${REDIRECT_URI}&client_secret=${client_secret}&code=${code}&response_type=code&prompt=consent`)
}