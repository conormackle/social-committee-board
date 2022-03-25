import { useEffect, useState } from 'react'
import { getAll } from '../../api'
import { Poll } from './Poll'
import './index.scss'

export default function Polls() {
  const [allPolls, setAllPolls] = useState(null)
  const [submitted, setSubmitted] = useState({})

  useEffect(() => {
    const getData = async () => {
      const { data } = await getAll('polls')
      setAllPolls(data.response)
    }
    getData()
  }, [])

  return (
    <div>
      <h1>Polls</h1>
      {allPolls?.map((poll) => (
        <Poll
          key={poll.id}
          {...poll}
          submitted={submitted[poll.id]}
          onSubmit={() => setSubmitted((prev) => ({ ...prev, [poll.id]: true }))}
        />
      ))}
    </div>
  )
}
