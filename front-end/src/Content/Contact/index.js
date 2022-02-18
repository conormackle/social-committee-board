import { useState } from 'react'
import { CSSTransition } from 'react-transition-group'
import ContactButton from './ContactButton'
import ContactForm from './ContactForm'

export default function Contact() {
  const [showInput, setShowInput] = useState(false)

  function toggleInput() {
    setShowInput((prev) => !prev)
  }

  return (
    <div className="contact">
      <CSSTransition in={showInput} timeout={200} classNames="fade-in" unmountOnExit>
        <ContactForm close={toggleInput} />
      </CSSTransition>
      {!false && <ContactButton toggle={toggleInput} />}
    </div>
  )
}
