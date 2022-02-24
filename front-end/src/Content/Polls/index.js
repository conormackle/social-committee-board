import { useEffect, useState } from 'react'
import { getAll } from '../../api'
import Section from '../../Layout/Section'

const Poll = ({ name, details, pollOptions }) => (
  <Section header={name}>
    <p>{details}</p>
    {pollOptions.map(option => (
      <div>
        <input type="radio" />
        {option.name}
      </div>
    ))}
  </Section>
)

export default function Polls() {
  const [allPolls, setAllPolls] = useState(null)

  useEffect(() => {
    const getData = async () => {
      const { data } = await getAll('polls')
      console.log(data)
      setAllPolls(data.response)
    }
    getData()
  }, [])
  return (
    <div>
      <h1>Polls</h1>
      {allPolls && allPolls.map(poll => (
        <Poll key={poll.id} {...poll} />
      ))}
    </div>
  )
}