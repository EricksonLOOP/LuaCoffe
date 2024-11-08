import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Inicio from './content/pages/inicio'


createRoot(document.getElementById('root')!).render(
  <Router>
    <Routes>
      <Route path='/' element={<Inicio />}></Route>
    </Routes>
  </Router>,
)
