import { request } from "http";
import { useState } from "react";
import { useForm } from "react-hook-form";

interface FormData {
  fromAddress: string,
  toAddress: string,
  ccAddresses?: string,
  subject: string,
  content: string,
  importance: Importance
}

interface Request {
  fromAddress: string,
  toAddress: string,
  ccAddresses: string[],
  subject: string,
  content: string,
  importance: Importance
}

enum Importance {
  LOW = 'LOW',
  NORMAL = 'NORMAL',
  HIGH = 'HIGH'
}

const cancelText = "Cancel email submit!";

const App = () => {
  const { handleSubmit, reset, register } = useForm<FormData>();
  const [loading, setLoading] = useState<boolean>(false);
  const [apiErrorMessage, setApiErrorMessage] = useState<string>('');

  const onSubmit = (formData: FormData)  => {
    setLoading(true);
    fetch('/api/email', 
      {
        method: 'POST', 
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(mapFormDataToRequest(formData))
      }).then(response => {
          if(response.status == 201) {
              resetForm();
              setApiErrorMessage('');
              return;
          }
          response.text().then(errors => setApiErrorMessage(errors));
      }).finally(() => setLoading(false))
  };

  const cancelDialog = () => {
    if (window.confirm(cancelText)) {
      resetForm()
    }
  }

  const resetForm = () => {
    reset();
  }

  return (
    <div className="App">
      <form onSubmit={handleSubmit(onSubmit)}>
        <h2>Sent email</h2>
        {apiErrorMessage && <span className="errorMsg">{apiErrorMessage}</span>}
        <br/>
        <div className="form-control">
          <label>From address*</label>
          <input type="email" required maxLength={320} {...register("fromAddress")}/>
        </div>
        <div className="form-control">
          <label>To address*</label>
          <input type="email" required maxLength={320} {...register("toAddress")}/>
        </div>
        <div className="form-control">
          <label>CC addresses</label>
          <input type="email" multiple required {...register("ccAddresses")}/>
        </div>
        <div className="form-control">
          <label>Subject*</label>
          <input type="text" required maxLength={255} {...register("subject")}/>
        </div>
        <div className="form-control">
          <label>Importance*</label>
          <select id="importance" required {...register("importance")}>
            {
              Object.keys(Importance).map((item) => {return <option key={item} value={item}>{item}</option>;}) 
            }
          </select>
        </div>
        <div className="form-control">
          <label>Content</label>
          <textarea rows={10} {...register("content")}/>
        </div>
        <div className="form-control inline">
          <button type="submit" disabled={loading}>Submit</button>
          <button type="button" onClick={cancelDialog} disabled={loading}>Cancel</button>
        </div>
      </form>
    </div>
  );
}

const mapFormDataToRequest = (data:FormData): Request => {
  return {
    toAddress: data.toAddress,
    fromAddress: data.fromAddress,
    ccAddresses: mapToArray(data.ccAddresses),
    subject: data.subject,
    importance: data.importance,
    content: data.content
  }
}

const mapToArray = (value?: string, separator: string = ','): string[] => {
  if(!value) {
    return []
  }
  return value.split(separator)
}

export default App;
