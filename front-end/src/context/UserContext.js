import { createContext, useState, useEffect } from 'react'
import queryString from 'query-string'
import { useLocation, useNavigate } from 'react-router-dom'
import { isAuthenticated, setAccessToken, setRefreshToken, getZohoToken, setAccessTokenExpiry } from '../auth'

export const UserContext = createContext()

export const UserProvider = ({ children }) => {
  const [user, setUser] = useState(null)
  const navigate= useNavigate()
  const location = useLocation()

  useEffect(() => {
    const code = queryString.parse(location.search)?.code
    const getToken = async () => {
      const { data } = await getZohoToken(code)
      console.log(data)
      if (data.access_token) {
        setAccessToken(`Zoho-oauthtoken ${data.access_token}`)
        setAccessTokenExpiry(data.expires_in)
        if (data.refresh_token) {
          setRefreshToken(`Zoho-refresh-token ${data.refresh_token}`)
        }
        navigate(isAuthenticated() ? '/' : '/login')
      }
    }
    if (code) getToken()
  }, [location, navigate])

  return (
    <UserContext.Provider value={ user }>
      {children}
    </UserContext.Provider>
  )
}