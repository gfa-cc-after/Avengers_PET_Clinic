
const ProfileEdit = () => {
  return (
    <div>
      <h1>Edit Profile</h1>
      <form>
        <div>
          <label htmlFor="username">Username:</label>
          <input type="text" id="username" name="username" />
        </div>
        <div>
          <label htmlFor="email">Email:</label>
          <input type="email" id="email" name="email" />
        </div>
        <button type="submit">Save Changes</button>
      </form>
    </div>
  );
};

export default ProfileEdit;