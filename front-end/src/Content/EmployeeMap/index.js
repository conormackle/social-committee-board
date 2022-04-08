import ReactMapGL, { Marker } from 'react-map-gl'
import { useState } from 'react'
import "mapbox-gl/dist/mapbox-gl.css";
import './index.scss'

const mapStyle = {
  width: '100%',
  height: '100%'
}

const mapboxAccessToken = process.env.REACT_APP_MAPBOX_ACCESS_TOKEN

export default function EmployeeMap() {
  const [markers, setMarkers] = useState([
    {
      name: 'Belfast',
      longitude: -5.934033750117038,
      latitude: 54.59882869880269
    },
    {
      name: 'New York',
      longitude: -74,
      latitude: 40.721
    },
    {
      name: 'London',
      longitude: 0,
      latitude: 50.5
    },
    {
      name: 'Hong Kong',
      longitude: 114.16,
      latitude: 22.3
    },
    {
      name: 'Singapore',
      longitude: 103.8,
      latitude: 1.35
    }
  ])

  const RenderMarker = ({ name, longitude, latitude }) => {
    return (
      <Marker
        key={name}
        longitude={longitude}
        latitude={latitude}
        pitchAlignment={'map'}
      >
        <div className="marker"><span></span></div>
      </Marker>
    )
  }

  return (
    <div className="map-container">
      <ReactMapGL
        mapboxAccessToken={mapboxAccessToken}
        mapStyle="mapbox://styles/mapbox/streets-v11"
        style={mapStyle}
        initialViewState={{
          longitude: 0,
          latitude: 45,
          zoom: 0
        }}
      >
        {markers.map(RenderMarker)}
      </ReactMapGL>
    </div>
  )
}