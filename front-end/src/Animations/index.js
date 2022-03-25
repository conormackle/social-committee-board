import { CSSTransition } from 'react-transition-group'
import './index.scss'

export function FadeIn({ render, noExitAnimation, children }) {
  return (
    <>
      {(!noExitAnimation || render) && (
        <CSSTransition in={render} timeout={200} classNames="fade-in" unmountOnExit>
          {children}
        </CSSTransition>
      )}
    </>
  )
}
