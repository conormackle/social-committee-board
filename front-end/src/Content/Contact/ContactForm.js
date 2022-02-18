import { CloseOutlined, SendOutlined } from '@ant-design/icons'
import { Button, Input } from 'antd'
import { useState } from 'react'

export default function ContactForm({ close }) {
  const [message, setMessage] = useState('')

  function sendMessage() {
      console.log(message)
  }

  return (
    <div className="form">
      <div>
        <h2>
          Contact Us!
          <Button icon={<CloseOutlined />} onClick={close} danger size="small" shape="circle" />
        </h2>
        <Input.TextArea value={message} onChange={(event) => setMessage(event.target.value)} maxLength="2000" />
        <Button icon={<SendOutlined />} type="primary" shape="circle" onClick={sendMessage} disabled={!message} />
      </div>
    </div>
  )
}
