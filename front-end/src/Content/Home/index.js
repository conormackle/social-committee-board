import { useEffect, useState } from 'react'
import { getAll } from '../../api'
import CardCarousel from '../../Cards/CardCarousel'
import MainCard from '../../Cards/MainCard'
import Section from '../../Layout/Section'
import './index.scss'

const cardData = [
  {
    title: 'An Educational Post',
    author: 'Jonny Press',
    date: '3 hours ago',
    comments: '2',
    tag: 'Educational',
    image: 'https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png'
  },
  {
    title: 'Sports Event',
    author: 'Sean Wilson',
    date: 'Yesterday',
    comments: '3',
    tag: 'Sports',
    image: 'https://i.stack.imgur.com/2OrtT.jpg'
  },
  {
    title: 'Dining Event',
    author: 'Cliona Logue',
    date: 'Last Week',
    comments: '6',
    tag: 'Food',
    image: 'https://cdn.theatlantic.com/media/mt/science/cat_caviar.jpg'
  },
  {
    title: 'Important Announcement',
    author: 'Connor Mackle',
    date: 'Last Week',
    comments: '2',
    tag: 'Announcement',
    image:
      'https://thumbs.dreamstime.com/b/cat-near-money-tree-glasses-sitting-next-to-white-background-123326058.jpg'
  }
]

export default function Home() {
  const [posts, setPosts] = useState(null)
  // ! endpoint broken
  useEffect(() => {
    const getData = async () => {
      const { data } = await getAll('posts')
      setPosts(data)
      console.log(data)
    }
    getData()
  }, [])

  return (
    <>
      <Section header="Latest Posts">
        <CardCarousel>
          {cardData.map((card) => (
            <MainCard
              key={card.title}
              title={card.title}
              author={card.author}
              date={card.date}
              comments={card.comments}
              tag={card.tag}
              image={card.image}
            />
          ))}
        </CardCarousel>
      </Section>
      <Section header="Announcements">
        <div>hello</div>
      </Section>
      <Section header="Section">
        <div>hello</div>
      </Section>
      <Section header="Section">
        <div>hello</div>
      </Section>
      <Section header="Section">
        <div>hello</div>
      </Section>
      <Section header="Section">
        <div>hello</div>
      </Section>
      <Section header="Section">
        <div>hello</div>
      </Section>
      <Section header="Section">
        <div>hello</div>
      </Section>
      <Section header="Section">
        <div>hello</div>
      </Section>
      <Section header="Section">
        <div>hello</div>
      </Section>
      <Section header="Section">
        <div>hello</div>
      </Section>
      <Section header="Section">
        <div>hello</div>
      </Section>
      <Section header="Section">
        <div>hello</div>
      </Section>
      <Section header="Section">
        <div>hello</div>
      </Section>
      <Section header="Section">
        <div>hello</div>
      </Section>
      <Section header="Section">
        <div>hello</div>
      </Section>
      <Section header="Section">
        <div>hello</div>
      </Section>
      <Section header="Section">
        <div>hello</div>
      </Section>
      <Section header="Section">
        <div>hello</div>
      </Section>
      <Section header="Section">
        <div>hello</div>
      </Section>
      <Section header="Section">
        <div>hello</div>
      </Section>
      <Section header="Section">
        <div>hello</div>
      </Section>
    </>
  )
}
