import { useState } from 'react'
import { Poll } from '../Poll'

export default function CurrentPoll() {
  const [submitted, setSubmitted] = useState(false)
  return (
    <Poll
      name="Choose a colour"
      details="Which is your favourite?"
      pollOptions={['Red', 'Green', 'Blue']}
      onSubmit={() => setSubmitted(true)}
      submitted={submitted}
    />
  )
}
