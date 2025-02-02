import { useForm } from "react-hook-form";
import { useBookData } from "./BookContext";
import { post } from "../helpers/post";
import { useNavigate } from "react-router";
import { useState } from "react";
import ErrorServer from "./ErrorServer";
import Success from "./Success";

export const Registration = () => {
    const {register,handleSubmit,watch,reset,formState:{errors},} = useForm();
    const navigate = useNavigate();
    
    const {setError,setMembers,setSuccess} = useBookData();
    const password = watch("password");
    const [email,setEmail] = useState("");
    const [isSubmitting, setIsSubmitting] = useState(false);


    const checkEmailAvailability = async (email) => {
        const response = await fetch(`http://localhost:8080/api/members/check-email?email=${email}`);
        return await response.json()
    }

    const handleEmailChange = async (e) => {
        setEmail(email);
        const emailExists = await checkEmailAvailability(e.target.value);
        if(emailExists){
            setError("Email is already in use");
        }else{
            setError("");
        }
    }


    const onSubmit = async (data) => {
        try{ 
            const {repeatPassword,...rest} = data;
            setIsSubmitting(true);
            const emailExists = await checkEmailAvailability(rest.email);
            if(emailExists){
                setError("Email is already in use");
                setIsSubmitting(false);
                return;
            }
            await post(rest)
            reset();
            setSuccess("Registration Successful")
            setTimeout(() => {
                setSuccess("You will be redirected to login page shortly") 
                setTimeout(() => {
                    setSuccess("");
                    navigate("/login")
                },1500)
            },3000)
        }
        catch(err){
            setError(err.meesage || "Something went wrong");
            setTimeout(() => {
                setError("");
            }, 3000);
        }
        finally{
            setIsSubmitting(false);
        }
    }


  return (
    <>
          <ErrorServer />
          <Success />
<div className="flex mx-auto justify-center items-center pt-10 flex-col text-center">
    <h2 className="text-3xl font-bold mb-5">Registration</h2>
    <form onSubmit={handleSubmit(onSubmit)}>
        <p className="label">Name:</p>
    <input className="input input:focus" type="text" placeholder="Enter your name" {...register("name",{required:"Name is required", pattern: {
        value: /^.{3,100}$/,
        message: "Name must be between 3 and 100 characters"
    }})} />
    {errors.name && <p className="sm-error">{errors.name.message}</p>}
    <p className="label">Email:</p>
    <input onChange={handleEmailChange} className="input input:focus" type="email" placeholder="Enter your email" {...register("email",{required:"Email is required", pattern: {
        value: /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i,
        message: "Invalid email address"
    }})} />
    {errors.email && <p className="sm-error">{errors.email.message}</p>}
    <p className="label">Password:</p>
    <input className="input input:focus" type="password" placeholder="Enter your password" {...register("password",{required:"password is required", pattern: {
        value: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/,
        message: "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one number, and one special character"
    }})} />
    {errors.password && <p className="sm-error">{errors.password.message}</p>}
    <p className="label">Repeat password:</p>
    <input className="input input:focus" type="password" placeholder="Repeat your password" {...register("repeatPassword",{required:"Repeat password is required",
    validate: (value) => value === password || "The passwords do not match" , pattern: {
        value: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/ ,
        message: "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one number, and one special character",
    
    }})} />
    {errors.repeatPassword && <p className="sm-error">{errors.repeatPassword.message}</p>}
    <button className={`super-btn mt-5 border-2 ${isSubmitting ? "cursor-not-allowed, super-btn-disabled" : ""}`} disabled={isSubmitting} type="submit" value="submit">Submit</button>
    </form>
    
    
</div>

    </>
    
  )
}