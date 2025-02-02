import { useState } from "react";
import { useForm } from "react-hook-form";
import { useBookData } from "./BookContext";
import ErrorServer from "./ErrorServer";
import Success from "./Success";
import { getMembers } from "../helpers/getMembers";

export const Login = () => {
    const {members,setMembers,error , setError, success, setSuccess,navigate} = useBookData();
    const {register,handleSubmit,reset,formState:{errors},} = useForm();
      const [isSubmitting, setIsSubmitting] = useState(false);

      const checkEmail = async (email) => {
        const response = await fetch(`http://localhost:8080/api/members/check-email?email=${email}`);
        return await response.json();
    
    }

    const checkPassword = async (password) => {
        const response = await fetch(`http://localhost:8080/api/members/check-password?password=${password}`);
        return await response.json();
    }

    const onSubmit = async (data) => {
        try {
            setIsSubmitting(true);
            const response = await fetch('http://localhost:8080/api/members/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    email: data.email,
                    password: data.password
                })
            });
    
            console.log("Response status:", response.status);
    
            if (!response.ok) {
                const errorText = await response.text();
                throw new Error(errorText || "Login failed");
            }
    
            const member = await response.json();
            
            setSuccess("Login successful");
            setTimeout(() => {
                setSuccess("");
                setTimeout(() => {
                    navigate("/");
                }, 1500);
            }, 3000);
            reset();
        } catch (err) {
            setError("An error occurred during login");
            setTimeout(() => {
                setError("");
            }, 3000);
        } finally {
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
    {errors.name && <p className="sm-error">{errors.name.message}</p>}
    <p className="label">Email:</p>
    <input className="input input:focus" type="email" placeholder="Enter your email" {...register("email",{required:"Email is required", pattern: {
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
    <button className={`super-btn mt-5 border-2 ${isSubmitting ? "cursor-not-allowed, super-btn-disabled" : ""}`} disabled={isSubmitting} type="submit" value="submit">Login</button>
    <button className="super-btn mt-5 border-2" onClick={() => navigate("/")}>Homepage</button>
    </form>
    
    
</div>
</>

  );
}