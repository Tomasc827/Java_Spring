import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import { BrowserRouter, Route, Routes } from 'react-router'
import { BookProviders } from './components/BookContext.jsx'
import { Registration } from './components/Registration.jsx'
import { Homepage } from './components/Homepage.jsx'
import { Login } from './components/Login.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
   <BrowserRouter>
   <BookProviders>
    <Routes>
      <Route path='/' element={<App />}>
      <Route index element={<Homepage />} />
      <Route path='/login' element={<Login />} />
      <Route path='/registration' element={<Registration />} />
      </Route>
    </Routes>
    </BookProviders>
    </BrowserRouter>
  </StrictMode>,
)
