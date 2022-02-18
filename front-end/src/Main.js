import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import TopNavigation from './TopNavigation'
import SideNavigation from './SideNavigation'
import Home from './Content/Home'
import { SmallCalendar } from './Content/Calendars/SmallCalendar'
import Contact from './Content/Contact'

export default function Main() {
  return (
    <Router>
      <TopNavigation />
      <SideNavigation />
      <div className="content">
        <div className="primary-content">
          <Routes>
            <Route exact path="/" element={<Home />} />
          </Routes>
        </div>
      </div>
      <div className="pinned-content">
        <SmallCalendar />
      </div>
      <Contact />
    </Router>
  )
}
