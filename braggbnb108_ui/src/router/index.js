import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import Propertys from  '@/pages/Propertys.vue';
import PropertyDetail from  '@/pages/PropertyDetail.vue';
import Hosts from  '@/pages/Hosts.vue';
import HostDetail from  '@/pages/HostDetail.vue';
import Guests from  '@/pages/Guests.vue';
import GuestDetail from  '@/pages/GuestDetail.vue';
import Reservations from  '@/pages/Reservations.vue';
import ReservationDetail from  '@/pages/ReservationDetail.vue';
import Reviews from  '@/pages/Reviews.vue';
import ReviewDetail from  '@/pages/ReviewDetail.vue';
import Payments from  '@/pages/Payments.vue';
import PaymentDetail from  '@/pages/PaymentDetail.vue';
import Photos from  '@/pages/Photos.vue';
import PhotoDetail from  '@/pages/PhotoDetail.vue';
import Amenitys from  '@/pages/Amenitys.vue';
import AmenityDetail from  '@/pages/AmenityDetail.vue';
import Locations from  '@/pages/Locations.vue';
import LocationDetail from  '@/pages/LocationDetail.vue';
import BookingRequests from  '@/pages/BookingRequests.vue';
import BookingRequestDetail from  '@/pages/BookingRequestDetail.vue';
import CancellationPolicys from  '@/pages/CancellationPolicys.vue';
import CancellationPolicyDetail from  '@/pages/CancellationPolicyDetail.vue';
import HostReviews from  '@/pages/HostReviews.vue';
import HostReviewDetail from  '@/pages/HostReviewDetail.vue';
import Notifications from  '@/pages/Notifications.vue';
import NotificationDetail from  '@/pages/NotificationDetail.vue';
import Messages from  '@/pages/Messages.vue';
import MessageDetail from  '@/pages/MessageDetail.vue';
import Availabilitys from  '@/pages/Availabilitys.vue';
import AvailabilityDetail from  '@/pages/AvailabilityDetail.vue';
import CleaningFees from  '@/pages/CleaningFees.vue';
import CleaningFeeDetail from  '@/pages/CleaningFeeDetail.vue';
import SecurityDeposits from  '@/pages/SecurityDeposits.vue';
import SecurityDepositDetail from  '@/pages/SecurityDepositDetail.vue';
import SavedPropertys from  '@/pages/SavedPropertys.vue';
import SavedPropertyDetail from  '@/pages/SavedPropertyDetail.vue';
import SupportTickets from  '@/pages/SupportTickets.vue';
import SupportTicketDetail from  '@/pages/SupportTicketDetail.vue';
import Promotions from  '@/pages/Promotions.vue';
import PromotionDetail from  '@/pages/PromotionDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/propertys',
																				  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/propertys',
		name: 'Propertys',
		layout: DefaultLayout,
		component: Propertys,
	},
	{
	    path: '/property/:propertyId', 
	    name: 'PropertyDetail',
		layout: DefaultLayout,
	    component: PropertyDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/hosts',
		name: 'Hosts',
		layout: DefaultLayout,
		component: Hosts,
	},
	{
	    path: '/host/:hostId', 
	    name: 'HostDetail',
		layout: DefaultLayout,
	    component: HostDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/guests',
		name: 'Guests',
		layout: DefaultLayout,
		component: Guests,
	},
	{
	    path: '/guest/:guestId', 
	    name: 'GuestDetail',
		layout: DefaultLayout,
	    component: GuestDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/reservations',
		name: 'Reservations',
		layout: DefaultLayout,
		component: Reservations,
	},
	{
	    path: '/reservation/:reservationId', 
	    name: 'ReservationDetail',
		layout: DefaultLayout,
	    component: ReservationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/reviews',
		name: 'Reviews',
		layout: DefaultLayout,
		component: Reviews,
	},
	{
	    path: '/review/:reviewId', 
	    name: 'ReviewDetail',
		layout: DefaultLayout,
	    component: ReviewDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/payments',
		name: 'Payments',
		layout: DefaultLayout,
		component: Payments,
	},
	{
	    path: '/payment/:paymentId', 
	    name: 'PaymentDetail',
		layout: DefaultLayout,
	    component: PaymentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/photos',
		name: 'Photos',
		layout: DefaultLayout,
		component: Photos,
	},
	{
	    path: '/photo/:photoId', 
	    name: 'PhotoDetail',
		layout: DefaultLayout,
	    component: PhotoDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/amenitys',
		name: 'Amenitys',
		layout: DefaultLayout,
		component: Amenitys,
	},
	{
	    path: '/amenity/:amenityId', 
	    name: 'AmenityDetail',
		layout: DefaultLayout,
	    component: AmenityDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/locations',
		name: 'Locations',
		layout: DefaultLayout,
		component: Locations,
	},
	{
	    path: '/location/:locationId', 
	    name: 'LocationDetail',
		layout: DefaultLayout,
	    component: LocationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/bookingRequests',
		name: 'BookingRequests',
		layout: DefaultLayout,
		component: BookingRequests,
	},
	{
	    path: '/bookingRequest/:bookingRequestId', 
	    name: 'BookingRequestDetail',
		layout: DefaultLayout,
	    component: BookingRequestDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/cancellationPolicys',
		name: 'CancellationPolicys',
		layout: DefaultLayout,
		component: CancellationPolicys,
	},
	{
	    path: '/cancellationPolicy/:cancellationPolicyId', 
	    name: 'CancellationPolicyDetail',
		layout: DefaultLayout,
	    component: CancellationPolicyDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/hostReviews',
		name: 'HostReviews',
		layout: DefaultLayout,
		component: HostReviews,
	},
	{
	    path: '/hostReview/:hostReviewId', 
	    name: 'HostReviewDetail',
		layout: DefaultLayout,
	    component: HostReviewDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/notifications',
		name: 'Notifications',
		layout: DefaultLayout,
		component: Notifications,
	},
	{
	    path: '/notification/:notificationId', 
	    name: 'NotificationDetail',
		layout: DefaultLayout,
	    component: NotificationDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/messages',
		name: 'Messages',
		layout: DefaultLayout,
		component: Messages,
	},
	{
	    path: '/message/:messageId', 
	    name: 'MessageDetail',
		layout: DefaultLayout,
	    component: MessageDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/availabilitys',
		name: 'Availabilitys',
		layout: DefaultLayout,
		component: Availabilitys,
	},
	{
	    path: '/availability/:availabilityId', 
	    name: 'AvailabilityDetail',
		layout: DefaultLayout,
	    component: AvailabilityDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/cleaningFees',
		name: 'CleaningFees',
		layout: DefaultLayout,
		component: CleaningFees,
	},
	{
	    path: '/cleaningFee/:cleaningFeeId', 
	    name: 'CleaningFeeDetail',
		layout: DefaultLayout,
	    component: CleaningFeeDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/securityDeposits',
		name: 'SecurityDeposits',
		layout: DefaultLayout,
		component: SecurityDeposits,
	},
	{
	    path: '/securityDeposit/:securityDepositId', 
	    name: 'SecurityDepositDetail',
		layout: DefaultLayout,
	    component: SecurityDepositDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/savedPropertys',
		name: 'SavedPropertys',
		layout: DefaultLayout,
		component: SavedPropertys,
	},
	{
	    path: '/savedProperty/:savedPropertyId', 
	    name: 'SavedPropertyDetail',
		layout: DefaultLayout,
	    component: SavedPropertyDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/supportTickets',
		name: 'SupportTickets',
		layout: DefaultLayout,
		component: SupportTickets,
	},
	{
	    path: '/supportTicket/:supportTicketId', 
	    name: 'SupportTicketDetail',
		layout: DefaultLayout,
	    component: SupportTicketDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/promotions',
		name: 'Promotions',
		layout: DefaultLayout,
		component: Promotions,
	},
	{
	    path: '/promotion/:promotionId', 
	    name: 'PromotionDetail',
		layout: DefaultLayout,
	    component: PromotionDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;
