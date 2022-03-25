import { createContext, useState, useEffect } from 'react'
import { getAll } from '../api'

export const EventsContext = createContext()

export const EventsProvider = (props) => {
  const [events, setEvents] = useState(null)

  useEffect(() => {
    try {
      const getData = async () => {
        const { data } = await getAll('events')
        setEvents(data.response)
      }
      getData()
    } catch (err) {
      console.log(err.response.data)
    }
  }, [])

  return (
    <EventsContext.Provider value={{ events }}>
      {props.children}
    </EventsContext.Provider>
  )
}