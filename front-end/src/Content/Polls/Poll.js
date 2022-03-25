import { useState } from 'react'
import Section from '../../Layout/Section'
import { Button, Radio, Space } from 'antd'

export function Poll({ name, details, pollOptions, submitted, onSubmit }) {
  const [selected, setSelected] = useState('')

  return (
    <Section header={name}>
      {submitted ? (
        <span>Thank you! Your response for this poll has been submitted.</span>
      ) : (
        <>
          <p>{details}</p>
          <div className="poll-radio">
            <Radio.Group onChange={setSelected}>
              <Space direction="vertical">
                {pollOptions.map((option) => (
                  <Radio key={option} value={option}>{option}</Radio>
                ))}
              </Space>
            </Radio.Group>
          </div>
          <Button type="primary" disabled={!selected} onClick={onSubmit}>
            Submit
          </Button>
        </>
      )}
    </Section>
  )
}
