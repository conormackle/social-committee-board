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
import EventsUpcoming from './Content/Events/Upcoming'
import EventsCalendar from './Content/Events/Calendar'
import Contact from './Content/Contact'
import NotFound from './NotFound'
import { UserProvider } from './context/UserContext'
import Login from './Content/Login'
import PrivateRoute from './Content/Reusable/PrivateRoute'

export default function Main() {
  return (
    <Router>
      <UserProvider>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="*" element={
            <EventsProvider>
              <TopNavigation />
              <SideNavigation />
              <div className="content">
                <div className="primary-content">
                  <Routes>
                    <Route exact path="/" element={<PrivateRoute><Home /></PrivateRoute>} />
                    <Route path="events/upcoming" element={<PrivateRoute><EventsUpcoming /></PrivateRoute>} />
                    <Route path="events/calendar" element={<PrivateRoute><EventsCalendar /></PrivateRoute>} />
                    <Route path="projects" element={<PrivateRoute><Projects /></PrivateRoute>} />
                    <Route path="polls" element={<PrivateRoute><Polls /></PrivateRoute>} />
                    <Route path="*" element={<PrivateRoute><NotFound /></PrivateRoute>} />
                  </Routes>
                </div>
              </div>
              <div className="pinned-content">
                <Routes>
                  <Route
                    path="/"
                    element={
                      <PrivateRoute>
                        <SmallCalendar />
                        <EmployeeMap />
                        <CurrentPoll />
                      </PrivateRoute>
                    }
                  />
                  <Route exact path="*" element={<></>} />
                </Routes>
              </div>
            </EventsProvider>
          } />
        </Routes>
      </UserProvider>
      <Contact />
    </Router>
  )
}
