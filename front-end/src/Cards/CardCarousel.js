import { LeftCircleOutlined, RightCircleOutlined } from '@ant-design/icons'
import { Carousel } from 'antd'
import _ from 'lodash'
import './index.css'

export default function CardCarousel(props) {
  const { children } = props
  return (
    <div className="card-carousel">
      <Carousel
        autoplay
        arrows
        draggable
        prevArrow={<LeftCircleOutlined />}
        nextArrow={<RightCircleOutlined />}
      >
        {_.chunk(children, 3).map((sublist, index) => (
          <div key={index} className='card-container'>{sublist}</div>
        ))}
      </Carousel>
    </div>
  )
}
