import { Menu } from 'antd'
import { Link } from 'react-router-dom'
import { useLocation } from 'react-router'
import './index.scss'

export default function SideNavigation() {
  const location = useLocation()

  return (
    <div className="side-nav">
      <Menu mode="inline" defaultSelectedKeys={[location.pathname]} defaultOpenKeys={['sub1']}>
        <Menu.Item key="/"><Link to="/">Home</Link></Menu.Item>
        <Menu.Item key="/events"><Link to="/events">Events</Link></Menu.Item>
        <Menu.Item key="/projects"><Link to="/projects">Projects</Link></Menu.Item>
        <Menu.Item key="/polls"><Link to="/polls">Polls</Link></Menu.Item>
      </Menu>
    </div>
  )
}
