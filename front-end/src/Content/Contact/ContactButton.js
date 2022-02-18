import { Button, Tooltip } from 'antd'
import { CommentOutlined } from '@ant-design/icons'
import './index.scss'

export default function ContactButton({ toggle }) {
  return (
    <Tooltip title="Contact Us!">
      <Button
        className="show-button"
        type="primary"
        shape="circle"
        icon={<CommentOutlined />}
        size="large"
        onClick={toggle}
      />
    </Tooltip>
  )
}
