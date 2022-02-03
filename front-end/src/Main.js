import TopNavigation from './TopNavigation'
import SideNavigation from './SideNavigation'
import Home from './Content/Home'

export default function Main() {
  return (
    <div>
      <TopNavigation />
      <SideNavigation />
      <div className="content">
        <div className="primary-content">
          <Home />
        </div>
      </div>
      <div className="pinned-content">
        <div>some pinned content</div>
      </div>
    </div>
  )
}
