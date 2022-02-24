import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import TopNavigation from './TopNavigation'
import SideNavigation from './SideNavigation'
import Home from './Content/Home'
import { SmallCalendar } from './Content/Calendars/SmallCalendar'
import EmployeeMap from './Content/EmployeeMap'
import { EventsProvider } from './context/EventsContext'
import Polls from './Content/Polls'
import CurrentPoll from './Content/Polls/CurrentPoll'
import Projects from './Content/Projects'
import Events from './Content/Events'

export default function Main() {
  return (
    <Router>
      <EventsProvider>
        <TopNavigation />
        <SideNavigation />
        <div className="content">
          <div className="primary-content">
            <Routes>
              <Route exact path="/" element={<Home />} />
              <Route path="/events" element={<Events />} />
              <Route path="/projects" element={<Projects />} />
              <Route path="/polls" element={<Polls />} />
            </Routes>
          </div>
        </div>
        <div className="pinned-content">
          <SmallCalendar />
          <EmployeeMap />
          <CurrentPoll />
        </div>
      </EventsProvider>
    </Router>
  )
}
