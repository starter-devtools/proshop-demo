import {useState, useEffect} from 'react'
import { Link, useLocation, useNavigate } from 'react-router-dom'
import { useDispatch, useSelector } from 'react-redux'
import { Form, Button, Row, Col, FormGroup, FormLabel, FormControl } from 'react-bootstrap'
import { FormContainer } from '../components/FormContainer';
import { Loader } from '../components/Loader'
import { useRegisterMutation } from '../slices/user-api-slice';
import { setCredentials } from '../slices/auth-slice';
import { toast } from 'react-toastify';
import { AuthUserResponse } from '../api/UserResponse'

export const RegisterPage = () => {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');

  const dispatch = useDispatch(); //send to state
  const navigate = useNavigate();

  const [register, { isLoading }] = useRegisterMutation();

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

    if(password !== confirmPassword) {
      toast.error("Password don't match!");
    } else {
      try {
        const res = await register({ name, email, password, isAdmin: true });
        //TODO: bring in name and isAdmin from the BE
        const userInfo: AuthUserResponse = {
            name,
            email,
            isAdmin: false,
            accessToken: res.data
        };

        dispatch(setCredentials({ ...userInfo }));
        navigate(redirect);
      } catch (err) {
        toast.error(err?.data?.message || err.error);
      }
    }


  }

  return (
    <FormContainer>
        <h1>Sign Up</h1>

        <Form onSubmit={submitHandler}>
            <FormGroup controlId='name' className='my-3'>
                <FormLabel>Name</FormLabel>
                <FormControl
                    type='text'
                    placeholder='Enter name'
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                ></FormControl>
            </FormGroup>

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

            <FormGroup controlId='confirmPassword' className='my-3'>
                <FormLabel>Confirm Password</FormLabel>
                <FormControl
                    type='password'
                    placeholder='Confirm password'
                    value={confirmPassword}
                    onChange={(e) => setConfirmPassword(e.target.value)}
                ></FormControl>
            </FormGroup>

            <Button disabled={isLoading} type='submit' variant='primary'>
                Register
            </Button>

            {isLoading && <Loader />}

            <Row className='py-3'>
                <Col>
                    Already have an account? 
                    <Link to={redirect ? `/login?redirect=${redirect}` : '/login'}>
                        Login
                    </Link>
                </Col>
            </Row>
        </Form>
    </FormContainer>
  )
}
