CREATE TABLE [dbo].[Hotel](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[hotel_name] [nvarchar](50) NULL,
	[price] [int] NULL,
	[boss_name] [nvarchar](50) NULL,
	[phone] [nvarchar](50) NULL,
	[status] [nvarchar](50) NULL,
	[roomtype] [nvarchar](50) NULL
) ON [PRIMARY]
GO