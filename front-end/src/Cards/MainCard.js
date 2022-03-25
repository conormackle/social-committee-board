import { Card } from 'antd'
import { MessageFilled } from '@ant-design/icons'
import './index.scss'

export default function MainCard(props) {
  const { title, author, date, comments, tag, image } = props
  return (
    <Card
      className="card main"
      hoverable
      cover={
        <img
          alt="example"
          src={image}
        />
      }
    >
      <div className="card text">
        <div className="card title">{title}</div>
        <div className="card author">By {author}</div>
        <div className="card date">{date}</div>
        <div className="card comments">
          <MessageFilled />
          <span>{comments}</span>
        </div>
      </div>
      <div className="card tag">{tag}</div>
    </Card>
  )
}
