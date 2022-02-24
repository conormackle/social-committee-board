import { useState, useEffect } from 'react'
import { getAll } from '../../api'
import Section from '../../Layout/Section'

export default function Projects() {
  const [projects, setProjects] = useState(null)

  useEffect(() => {
    const getData = async () => {
      const { data } = await getAll('projects')
      setProjects(data.response)
      console.log(data.response)
    }
    getData()
  }, [])

  return (
    <div>
      <h1>Projects</h1>
      {projects && projects.map(project => (
        <Section key={project.id} header={project.details}>
          <h3>{project.details}</h3>
        </Section>
      ))}
    </div>
  )
}