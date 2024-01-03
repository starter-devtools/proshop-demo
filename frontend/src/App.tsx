import './App.scss'
import { Container } from 'react-bootstrap';
import {Header} from './components/Header'
import { Outlet } from 'react-router-dom';
import {Footer} from './components/Footer'

function App() {
  return (
    <>
      <Header />
      <main className='py-3'>
        <Container>
          <Outlet />
        </Container>
      </main>
      <Footer />
    </>
  )
}

export default App
