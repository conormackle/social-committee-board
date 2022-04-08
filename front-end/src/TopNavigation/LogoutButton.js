import { useNavigate } from "react-router-dom"
import { removeToken } from "../auth"

export default function LogoutButton() {
  const navigate = useNavigate()

  function handleLogout() {
    removeToken()
    navigate('/')
  }

  return (
    <button className="logout-button" onClick={handleLogout}>
      Log Out
    </button>
  )
}