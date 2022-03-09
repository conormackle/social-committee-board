import { useContext } from 'react'
import { EventsContext } from '../../../context/EventsContext'
import moment from 'moment'
import Section from '../../../Layout/Section'

export default function Projects() {
  const { events } = useContext(EventsContext)
  console.log(events)

  return (
    <div>
    <h1>Events</h1>
      {events && events.map(event => (
        <Section key={event.id} header={event.name}>
          <h4>{moment(event.date).format('ddd Do MMM YYYY')}</h4>
          <p>{event.details}</p>
        </Section>
      ))}
    </div>
  )
}