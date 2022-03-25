import { useState } from 'react'
import { Poll } from '../Poll'

export default function CurrentPoll() {
  const [submitted, setSubmitted] = useState(false)
  return (
    <Poll
      name="Choose a colour"
      details="Which is your favourite?"
      pollOptions={[
        { id: 0, name: 'Red', numberOfVotes: 0 },
        { id: 1, name: 'Green', numberOfVotes: 8 },
        { id: 2, name: 'Blue', numberOfVotes: 12 }
      ]}
      onSubmit={() => setSubmitted(true)}
      submitted={submitted}
    />
  )
}
