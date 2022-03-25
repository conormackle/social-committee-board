import { Link } from 'react-router-dom'
import './index.scss'

export default function index() {
  return (
    <div className="top-nav">
      <Link to="/">AquaQ Bulletin Board</Link>
    </div>
  )
}
