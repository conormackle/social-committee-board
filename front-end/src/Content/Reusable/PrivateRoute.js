import { Navigate } from 'react-router-dom'
import { isAuthenticated } from '../../auth'

export default function PrivateRoute({ children }) {
    const auth = isAuthenticated()
    return auth ? children : <Navigate to="/login"/>
}