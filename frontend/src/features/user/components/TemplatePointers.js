function TemplatePointers(){
      
    const TOKEN = JSON.parse(localStorage.getItem("token"))
    const username = TOKEN.username
    const role = TOKEN.role
    
    return(
        <>
         <h1 className="text-2xl mt-8 font-bold">Hello {username}!</h1>
         <p className="py-2 mt-4">✓ <span className="font-semibold">Welcome to Timperio new CRM!</span></p>
         <p className="py-2">✓ <span className="font-semibold">Everything you need to manage and grow your business is right here</span></p>
         <p className="py-2">✓ <span className="font-semibold">Get started by navigating to a page on the left sidebar</span></p>



         {/* <p className="py-2 mt-4">✓ <span className="font-semibold">Light/dark</span> mode toggle</p>
          <p className="py-2 ">✓ <span className="font-semibold">Redux toolkit</span> and other utility libraries configured</p>
          <p className="py-2">✓ <span className="font-semibold">Calendar, Modal, Sidebar </span> components</p>
          <p className="py-2  ">✓ User-friendly <span className="font-semibold">documentation</span></p>
          <p className="py-2  mb-4">✓ <span className="font-semibold">Daisy UI</span> components, <span className="font-semibold">Tailwind CSS</span> support</p> */}
        </>
    )
}

export default TemplatePointers