import { useState } from 'react'
import { FadeIn } from '../../Animations'
import ContactButton from './ContactButton'
import ContactForm from './ContactForm'

export default function Contact() {
  const [showInput, setShowInput] = useState(false)

  function toggleInput() {
    setShowInput((prev) => !prev)
  }

  return (
    <div className="contact">
      <FadeIn render={showInput}>
        <ContactForm close={toggleInput} />
      </FadeIn>
      <ContactButton toggle={toggleInput} />
    </div>
  )
}
