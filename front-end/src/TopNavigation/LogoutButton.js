import { useNavigate } from "react-router-dom"
import { logout } from "../auth"

export default function LogoutButton() {
  const navigate = useNavigate()

  function handleLogout() {
    logout()
    navigate('/login')
  }

  return (
    <button className="logout-button" onClick={handleLogout}>
      Log Out
    </button>
  )
}