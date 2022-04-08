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
import PrivateOutlet from './Content/Reusable/PrivateRoute'

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
                    <Route path="/" element={<PrivateOutlet/>}>
                      <Route exact path="/" element={<Home />} />
                      <Route path="events/upcoming" element={<EventsUpcoming />} />
                      <Route path="events/calendar" element={<EventsCalendar />} />
                      <Route path="projects" element={<Projects />} />
                      <Route path="polls" element={<Polls />} />
                      <Route path="*" element={<NotFound />} />
                    </Route>
                  </Routes>
                </div>
              </div>
              <div className="pinned-content">
                <Routes>
                  <Route path="/" element={<PrivateOutlet />} >
                    <Route path="/" element={
                      <>
                        <SmallCalendar />
                        <EmployeeMap />
                        <CurrentPoll />
                      </>
                    } />
                  </Route>
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
