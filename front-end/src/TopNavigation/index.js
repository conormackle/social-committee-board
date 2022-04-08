import { Link } from 'react-router-dom'
import { isAuthenticated } from '../auth'
import './index.scss'
import LoginButton from './LoginButton'
import LogoutButton from './LogoutButton'

export default function index() {

  return (
    <div className="top-nav">
      <Link to="/">AquaQ Bulletin Board</Link>
      {isAuthenticated() ? <LogoutButton /> : <LoginButton />}
    </div>
  )
}
