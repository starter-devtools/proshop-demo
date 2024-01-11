import {useState, useEffect} from 'react'
import { Link, useLocation, useNavigate } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'
import { Form, Button, Row, Col, FormGroup, FormLabel, FormControl } from 'react-bootstrap'
import { FormContainer } from '../components/FormContainer';
import { Loader } from '../components/Loader'

import { useLoginMutation } from '../slices/user-api-slice';
import { setCredentials } from '../slices/auth-slice';
import { toast } from 'react-toastify';
import { AuthResponse } from '../api/UserResponse'

export const LoginPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const dispatch = useDispatch(); //send to state
  const navigate = useNavigate();

  const [login, { isLoading }] = useLoginMutation();

  const { userInfo } = useSelector((state) => state.auth); //get from state

  const { search } = useLocation();
  const sp = new URLSearchParams(search); //get the URL search params
  const redirect = sp.get('redirect') || '/'; //if redirect is in the URL path

  useEffect(() => {
    //If the user is logged in, redirect them.
    if (userInfo?.accessToken?.data) {
      navigate(redirect);
    }
  }, [navigate, redirect, userInfo]);
  
  const submitHandler = async (e) => {
    e.preventDefault();
    try {
        const res = await login({ email, password });
        const userInfo: AuthResponse = {
            email,
            password,
            accessToken: res.data
        };

        dispatch(setCredentials({ ...userInfo }));
        navigate(redirect);
      } catch (err) {
        toast.error(err?.data?.message || err.error);
      }
  }

  return (
    <FormContainer>
        <h1>Sign In</h1>

        <Form onSubmit={submitHandler}>
            <FormGroup controlId='email' className='my-3'>
                <FormLabel>Email Address</FormLabel>
                <FormControl
                    type='email'
                    placeholder='Enter email'
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                ></FormControl>
            </FormGroup>

            <FormGroup controlId='password' className='my-3'>
                <FormLabel>Password</FormLabel>
                <FormControl
                    type='password'
                    placeholder='Enter password'
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                ></FormControl>
            </FormGroup>

            <Button disabled={isLoading} type='submit' variant='primary'>
                Sign In
            </Button>

            {isLoading && <Loader />}

            <Row className='py-3'>
                <Col>
                    New Customer? 
                    <Link to={redirect ? `/register?redirect=${redirect}` : '/register'}>
                        Register
                    </Link>
                </Col>
            </Row>
        </Form>
    </FormContainer>
  )
}
