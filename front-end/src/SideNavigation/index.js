import { Menu } from 'antd'
import { Link } from 'react-router-dom'
import { useLocation } from 'react-router'
import './index.scss'
import {
  BarChartOutlined,
  CalendarOutlined,
  ClockCircleOutlined,
  FundProjectionScreenOutlined,
  HomeOutlined,
  StarOutlined
} from '@ant-design/icons'

export default function SideNavigation() {
  const location = useLocation()

  return (
    <div className="side-nav">
      <Menu mode="inline" selectedKeys={[location.pathname]} defaultOpenKeys={['event-group']}>
        <Menu.Item key="/" icon={<HomeOutlined />}>
          <Link to="/">Home</Link>
        </Menu.Item>
        <Menu.SubMenu key="event-group" title="Events" icon={<StarOutlined />}>
          <Menu.Item key="/events/upcoming" icon={<ClockCircleOutlined />}>
            <Link to="events/upcoming">Upcoming</Link>
          </Menu.Item>
          <Menu.Item key="/events/calendar" icon={<CalendarOutlined />}>
            <Link to="events/calendar">Calendar</Link>
          </Menu.Item>
        </Menu.SubMenu>
        <Menu.Item key="/projects" icon={<FundProjectionScreenOutlined />}>
          <Link to="projects">Projects</Link>
        </Menu.Item>
        <Menu.Item key="/polls" icon={<BarChartOutlined />}>
          <Link to="polls">Polls</Link>
        </Menu.Item>
      </Menu>
    </div>
  )
}
