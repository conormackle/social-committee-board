import { Navigate, Outlet } from 'react-router-dom'
import { isAuthenticated } from '../../auth'

export default function PrivateRoute() {
    const auth = isAuthenticated()
    return auth ? <Outlet /> : <Navigate to="/login"/>
}