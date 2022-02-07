import './index.css'

export default function Section(props) {
  const { header, children } = props
  return (
    <div className="section">
      <h1 className="section-header">{header}</h1>
      {children}
    </div>
  )
}
