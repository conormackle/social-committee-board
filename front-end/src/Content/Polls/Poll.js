import { useState } from 'react'
import Section from '../../Layout/Section'
import { Button, Progress, Radio, Space } from 'antd'
import { FadeIn } from '../../Animations'

function PollResults({ pollOptions }) {
  function percentage(votes) {
    const total = pollOptions.reduce((previous, current) => previous + current.numberOfVotes, 0)
    return (100 * votes) / total
  }

  return (
    <div className="poll-results">
      {pollOptions.map((option) => (
        <div key={option.id} className="vote-option">
          <span className="name">{option.name}</span>
          <span className="percent">{percentage(option.numberOfVotes).toFixed()}%</span>
          <Progress percent={percentage(option.numberOfVotes)} showInfo={false} />
          <span className="total">
            {option.numberOfVotes} vote{option.numberOfVotes !== 1 ? 's' : ''}
          </span>
        </div>
      ))}
      <span>Thank you! Your response for this poll has been submitted.</span>
    </div>
  )
}

export function Poll({ name, details, pollOptions, submitted, onSubmit }) {
  const [selected, setSelected] = useState('')

  return (
    <Section header={name}>
      <FadeIn render={!submitted} noExitAnimation>
        <>
          <p>{details}</p>
          <div className="poll-radio">
            <Radio.Group onChange={setSelected}>
              <Space direction="vertical">
                {pollOptions.map((option) => (
                  <Radio key={option.id} value={option.name}>
                    {option.name}
                  </Radio>
                ))}
              </Space>
            </Radio.Group>
          </div>
          <Button type="primary" disabled={!selected} onClick={onSubmit}>
            Submit
          </Button>
        </>
      </FadeIn>

      <FadeIn render={submitted}>
        <PollResults pollOptions={pollOptions} />
      </FadeIn>
    </Section>
  )
}
