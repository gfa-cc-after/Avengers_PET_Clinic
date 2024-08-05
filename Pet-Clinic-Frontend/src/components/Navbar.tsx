import "./Navbar.css"
import { Link } from 'react-router-dom'
import { routes } from '../routes'
import logo from "../images/petclinic.png"

const Navbar = () => {
	return (
		<nav>
			<div className="navigation">
				<div className="nav-header">
					<img src={logo} alt="" />

				</div>
				<div className="nav-list">
					<ul>
						{
						routes.map((route) => {
							const { id, text, path } = route;
							return (
								<li key={id}>
									<Link to={path}>{text}</Link>
								</li>
							);
						})
						}
					</ul>
				</div>
			</div>
		</nav>
	)
}

export default Navbar