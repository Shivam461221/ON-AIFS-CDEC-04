import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import { CountProvider } from './Context/CountProvider.jsx'
import { BrowserRouter } from 'react-router-dom'
import { UserProvider } from './Context/UserProvider.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <BrowserRouter>
      <CountProvider>
        <UserProvider>
          <App />
        </UserProvider>
      </CountProvider>
    </BrowserRouter>
  </StrictMode>,
)
