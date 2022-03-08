import { Calendar, Popover } from 'antd'
import _ from 'lodash'
import { useContext } from 'react'
import { EventsContext } from '../../context/EventsContext'
import './index.scss'

const eventDates = [
  { day: 2, month: 3, type: 'orange', title: 'Event 1', description: 'This is an event' },
  { day: 7, month: 3, type: 'red', title: 'Event 2', description: 'This is an event' },
  { day: 12, month: 3, type: 'orange', title: 'Event 3', description: 'This is an event' },
  { day: 16, month: 3, type: 'red', title: 'Event 4', description: 'This is an event' },
  { day: 19, month: 3, type: 'green', title: 'Event 5', description: 'This is an event' },
  { day: 19, month: 3, type: 'green', title: 'Event 6', description: 'This is an event' },
  { day: 21, month: 3, type: 'orange', title: 'Event 7', description: 'This is an event' },
  { day: 25, month: 3, type: 'green', title: 'Event 7', description: 'This is an event' }
]

function getDay(date) {
  return date.format('DD')
}

function getMonth(date) {
  return date.format('MM')
}

export function SmallCalendar() {
  const { events } = useContext(EventsContext)
  console.log(events)

  function dateCell(date) {
    const day = parseInt(getDay(date))
    const month = parseInt(getMonth(date))
    const eventDay = _.find(eventDates, { day, month })

    return (
      <Popover title={eventDay?.title} content={eventDay?.description} trigger="hover">
        <span className={eventDay ? `event ${eventDay.type}` : ''}>{getDay(date)}</span>
      </Popover>
    )
  }

  return (
    <div className="small-calendar">
      <Calendar fullscreen={false} dateFullCellRender={dateCell} />
    </div>
  )
}
