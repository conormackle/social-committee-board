import { createContext, useState, useEffect } from 'react'
// import { getUser } from '../api'
import { getZohoToken } from '../oAuth'
import queryString from 'query-string'
import { useLocation } from 'react-router-dom'
import { getPayload, isAuthenticated, setAccessToken, setRefreshToken } from '../auth'

export const UserContext = createContext()

export const UserProvider = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(isAuthenticated())
  const [user, setUser] = useState(null)
  const location = useLocation()

  useEffect(() => {
    const code = queryString.parse(location.search)?.code
    const getToken = async () => {
      let response
      const { data } = await getZohoToken(code)
      console.log(data)
      if (data.access_token) {
        setAccessToken(`Zoho-oauthtoken ${data.access_token}`)
        setIsLoggedIn(isAuthenticated())
      }
      if (data.refresh_token) {
        setRefreshToken(data.refresh_token)
      }
    }
    if (code) getToken()
  }, [location])

  useEffect(() => {
    if (isLoggedIn) {
      const getData = async () => {
        const { data } = getPayload().sub
        // setUser(data)
        console.log(data)
      }
      getData()
    } else setUser(null) 
  }, [isLoggedIn])

  return (
    <UserContext.Provider value={ user }>
      {children}
    </UserContext.Provider>
  )
}