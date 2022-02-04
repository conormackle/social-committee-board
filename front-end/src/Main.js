import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'

import TopNavigation from './TopNavigation'
import SideNavigation from './SideNavigation'
import Home from './Content/Home'

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
        <div>some pinned content</div>
      </div>
    </Router>
  )
}
