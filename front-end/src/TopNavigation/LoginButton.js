export default function LoginButton() {
  async function handleLogin() {
    const domain = 'https://accounts.zoho.eu'
    const scope = 'ZOHOPEOPLE.forms.ALL,Aaaserver.profile.read'
    const responseType = 'code'
    const accessType = 'offline'
    const REDIRECT_URI = 'http://localhost:3000/'
    const zohoClientId = process.env.REACT_APP_CLIENT_ID
    const response = await fetch(
      `${domain}/oauth/v2/auth?scope=${scope}&client_id=${zohoClientId}&response_type=${responseType}&access_type=${accessType}&redirect_uri=${REDIRECT_URI}&prompt=consent`,
      { redirect: 'manual' }
    )
    window.location.replace(response.url)
  }

  return (
    <button className="login-button" onClick={handleLogin}>
      Log In
    </button>
  )
}