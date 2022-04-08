import { Navigate } from "react-router";
import { isAuthenticated, zohoRedirect } from "../../auth"
import './index.scss'

export default function Login() {
  const loggedIn = isAuthenticated()

  if (loggedIn) return <Navigate to="/" />

  async function handleLogin() {
    const { url } = await zohoRedirect()
    window.location.replace(url)
  }

  return (
    <div className="login-page">
      <div className="container">
        <h1>AquaQ Social Bulletin Board</h1>
        <button className="login-button" onClick={handleLogin}>
          Log In with Zoho
        </button>
      </div>
    </div>
  )

}
