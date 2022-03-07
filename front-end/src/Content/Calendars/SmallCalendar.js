import { Calendar } from 'antd'
import _ from 'lodash'
import { useContext } from 'react'
import { EventsContext } from '../../context/EventsContext'
import './index.scss'

const eventDates = [
  { day: 2, month: 2, type: 'community' },
  { day: 7, month: 2, type: 'holiday' },
  { day: 12, month: 2, type: 'community' },
  { day: 16, month: 2, type: 'holiday' },
  { day: 19, month: 2, type: 'sports' },
  { day: 19, month: 2, type: 'dining' },
  { day: 21, month: 2, type: 'community' },
  { day: 25, month: 2, type: 'sports' }
]

function getDay(date) {
  return date.format('DD')
}

function getMonth(date) {
  return date.format('MM')
}

function cellClass(date) {
  const day = parseInt(getDay(date))
  const month = parseInt(getMonth(date))
  const eventDay = _.find(eventDates, { day, month })
  if (eventDay) {
    return `event ${eventDay.type}`
  }
  return ''
}

function dateCell(date) {
  return <span className={cellClass(date)}>{getDay(date)}</span>
}

export function SmallCalendar() {
  const { events } = useContext(EventsContext)
  console.log(events)
  return (
    <div className="small-calendar">
      <Calendar fullscreen={false} dateFullCellRender={dateCell} />
    </div>
  )
}
