import { CaretLeftOutlined, CaretRightOutlined } from '@ant-design/icons'
import { Carousel } from 'antd'
import './index.scss'

export default function CardCarousel(props) {
  const { children } = props
  return (
    <div className="card-carousel">
      <Carousel
        autoplay
        arrows
        draggable
        slidesToShow={3}
        prevArrow={<CaretLeftOutlined />}
        nextArrow={<CaretRightOutlined />}
      >
          {children}
      </Carousel>
    </div>
  )
}
